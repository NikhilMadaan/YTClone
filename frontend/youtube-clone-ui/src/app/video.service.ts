import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient:HttpClient) { }

  uploadVideo(fileEntry: File):Observable<any>{
    //check upload video component ts for reference
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
    //HTTP post call
    return this.httpClient.post("http://localhost:8080/api/videos",formData);


  }
}
