import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MatChipInputEvent} from "@angular/material/chips";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../video.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {VideoDto} from "../VideoDto";

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class SaveVideoDetailsComponent implements OnInit {

  saveVideoDetails:FormGroup
  title:FormControl =new FormControl('');
  description:FormControl=new FormControl('');
  videoStatus:FormControl=new FormControl('');
  addOnBlur = true;
  selectedFile!: File  ;
  selectedFileName='';
  fileSelected=false;
  videoUrl!:string
  thumbnailUrl!:string

  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
   videoId: '';
  constructor(private activatedRoute:ActivatedRoute,private videoService:VideoService,
              private _snackBar: MatSnackBar) {
    this.videoId= this.activatedRoute.snapshot.params['videoId'];
    this.videoService.getVideo(this.videoId).subscribe(data=>{
      this.videoUrl=data.videoUrl;
      this.thumbnailUrl=data.thumbnailUrl
    })
    this.saveVideoDetails=new FormGroup({
      title:this.title,
      description:this.description,
      videoStatus:this.videoStatus

    })
  }

  ngOnInit(): void {
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    if (value) {
      this.tags.push( value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }


  onFileSelected($event: Event) {
    // @ts-ignore
    this.selectedFile=$event.target.files[0];
    // @ts-ignore
    this.selectedFileName=this.selectedFile.name;
  this.fileSelected=true
  }

  onUpload() {
    this.videoService.uploadThumbnail(this.selectedFile,this.videoId).subscribe(data=>{
      console.log(data);
      //show success notification
      this._snackBar.open("Thumbnail added successfully!!","OK");
    })
  }

  saveVideo() {
    //make call to video service to make a http call to backend
    const videoMetadata:VideoDto={
      "id":this.videoId,
      "title":this.saveVideoDetails.get('title')?.value,
      "description":this.saveVideoDetails.get('description')?.value,
      "tags":this.saveVideoDetails.get('tags')?.value,
      "videoStatus":this.saveVideoDetails.get('videoStatus')?.value,
      "videoUrl":this.videoUrl,
      "thumbnailUrl":this.thumbnailUrl

    }
    console.log(videoMetadata)
    this.videoService.saveVideo(videoMetadata).subscribe(data=>{
      console.log(data);
      this._snackBar.open("Metadata Saved","Ok")
    })
  }
}
