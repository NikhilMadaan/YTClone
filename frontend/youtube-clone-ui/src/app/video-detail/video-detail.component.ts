import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {VideoService} from "../video.service";

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent implements OnInit {
  videoAvailable:boolean=false
  videoId!:string
  videoUrl!:string
  videoTitle!:string
  videoDescription!:string
  tags:Array<string>=[];
  constructor(private activatedRoute:ActivatedRoute,private videoService:VideoService) {
    this.videoId= this.activatedRoute.snapshot.params['videoId'];
        this.videoService.getVideo(this.videoId).subscribe(data=>{
      this.videoUrl=data.videoUrl;
      this.videoAvailable=true
      console.log(this.videoUrl)
        this.videoTitle=  data.title
         this.videoDescription= data.description
         this.tags= data.tags

    })
  }


  ngOnInit(): void {
  }

}
