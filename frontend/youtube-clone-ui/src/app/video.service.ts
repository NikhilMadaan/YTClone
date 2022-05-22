import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient:HttpClient) { }

  uploadVideo(fileEntry: File):Observable<UploadVideoResponse>{
    //check upload video component ts for reference
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
    //HTTP post call
    return this.httpClient.post<UploadVideoResponse>("http://localhost:8080/api/videos",formData);

  }
  uploadThumbnail(fileEntry: File,videoId:string):Observable<String>{
    //check upload video component ts for reference
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
    formData.append('videoId', videoId);
    //HTTP post call
    return this.httpClient.post("http://localhost:8080/api/videos/thumbnail",formData,{
      responseType:'text'
    });

  }
}
