import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UploadService {
  
  private uploadUrl = 'http://localhost:8080/upload';  // Your backend endpoint

  constructor(private http: HttpClient) {}

  // Upload photo with session credentials
  uploadPhoto(file: File, uploadedBy: string): Observable<any> {
    const formData = new FormData();
    formData.append('file', file, file.name);
    formData.append('uploadedBy', uploadedBy);

    return this.http.post(this.uploadUrl, formData, {
      withCredentials: true, // ✅ Send session cookie (JSESSIONID)
      headers: new HttpHeaders({
        'Accept': 'application/json'
      })
    });
  }
}


// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class UploadService {
  
//   private uploadUrl = 'http://localhost:8080/upload';  // Replace with your actual backend URL

//   constructor(private http: HttpClient) {}

//   // Method to upload a photo
//   uploadPhoto(file: File, uploadedBy: string): Observable<any> {
//     const formData = new FormData();
//     formData.append('file', file, file.name);
//     formData.append('uploadedBy', uploadedBy);

//     return this.http.post(this.uploadUrl, formData);
//   }
// }







  // Existing uploadPhoto method...


// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class UploadService {
//   private uploadUrl = 'http://localhost:8080/upload'; // Your backend URL

//   constructor(private http: HttpClient) {}

//   uploadPhoto(file: File, uploadedBy: string): Observable<any> {
//     const formData = new FormData();
//     formData.append('file', file);
//     formData.append('uploadedBy', uploadedBy);

//     return this.http.post(this.uploadUrl, formData);
//   }
// }

// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class UploadService {

//   constructor() { }
// }
