import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Pic } from './pic.model';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}


  getPics(): Observable<Pic[]> {
    return this.http.get<Pic[]>(`${this.apiUrl}/pics`, { withCredentials: true }).pipe(
      catchError(this.handleError)
    );
  }

  ShowPics(): Observable<Pic[]> {
    return this.http.get<Pic[]>(`${this.apiUrl}/home`, { withCredentials: true }).pipe(
      catchError(this.handleError)
    );
  }

  updatePhotoStatus(id: number, status: string): Observable<void> {
    const body = { id, status };
    return this.http.put<void>(`${this.apiUrl}/pics`, body, { withCredentials: true }).pipe(
      catchError(this.handleError)
    );
  }

  deletePic(id: number): Observable<{ message: string }> {
    return this.http.delete<{ message: string }>(`${this.apiUrl}/pics?id=${id}`, { withCredentials: true }).pipe(
      catchError(this.handleError)
    );
  }

  getFileUrl(fileName: string): string {
    return `${this.apiUrl}/file/${fileName}`;
  }

  private handleError(error: HttpErrorResponse) {
    console.error('Server error:', error);
    return throwError(() => new Error('Something went wrong. Please try again later.'));
  }
}

// import { Injectable } from '@angular/core';
// import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
// import { Observable, throwError } from 'rxjs';
// import { catchError } from 'rxjs/operators';
// import { Pic } from './pic.model';

// @Injectable({
//   providedIn: 'root',
// })
// export class DataService {
//   private apiUrl = 'http://localhost:8080'; // Backend API URL

//   constructor(private http: HttpClient) {}

//   getPics(): Observable<Pic[]> {
//     return this.http.get<Pic[]>(`${this.apiUrl}/pics`, { withCredentials: true }).pipe(
//       catchError(this.handleError)
//     );
//   }

//   updatePhotoStatus(id: number, status: string): Observable<void> {
//     const url = `${this.apiUrl}/pics`;
//     const body = { id, status };
//     return this.http.put<void>(url, body, { withCredentials: true }).pipe(
//       catchError(this.handleError)
//     );
//   }

//   deletePic(id: number): Observable<{ message: string }> {
//     const url = `${this.apiUrl}/pics?id=${id}`;
//     return this.http.delete<{ message: string }>(url, { withCredentials: true }).pipe(
//       catchError(this.handleError)
//     );
//   }

//   getFileUrl(fileName: string): string {
//     return `${this.apiUrl}/file/${fileName}`;
//   }

//   private handleError(error: HttpErrorResponse) {
//     if (error.error instanceof ErrorEvent) {
//       // Client-side error
//       console.error('Client-side error:', error.error.message);
//       return throwError(() => new Error('Something went wrong. Please try again later.'));
//     } else {
//       // Server-side error
//       console.error(`Server-side error: ${error.status} - ${error.message}`);
//       return throwError(() => new Error('Something went wrong. Please try again later.'));
//     }
//   }
// }


// import { Injectable } from '@angular/core';
// import { HttpClient, HttpErrorResponse } from '@angular/common/http';
// import { Observable, throwError } from 'rxjs';
// import { catchError } from 'rxjs/operators';
// import { Pic } from './pic.model';

// @Injectable({
//   providedIn: 'root',
// })
// export class DataService {
//   private apiUrl = 'http://localhost:8080'; // Backend API URL

//   constructor(private http: HttpClient) {}

//   // Fetch all pictures
//   getPics(): Observable<Pic[]> {
//     return this.http.get<Pic[]>(`${this.apiUrl}/pics`).pipe(
//       catchError(this.handleError)
//     );
//   }

//   // Update picture status
//   updatePhotoStatus(id: number, status: string): Observable<void> {
//     const url = `${this.apiUrl}/pics`;
//     const body = { id, status };
//     return this.http.put<void>(url, body).pipe(
//       catchError(this.handleError)
//     );
//   }

//   // Get file URL for display/download purposes
//   getFileUrl(fileName: string): string {
//     return `${this.apiUrl}/file/${fileName}`;
//   }

//   // Delete picture by ID
//   deletePic(id: number): Observable<{ message: string }> {
//     const url = `${this.apiUrl}/pics?id=${id}`;
//     return this.http.delete<{ message: string }>(url).pipe(
//       catchError(this.handleError)
//     );
//   }

//   // Generic error handler
//   private handleError(error: HttpErrorResponse) {
//     console.error('API Error:', error);
//     if (error.error instanceof ErrorEvent) {
//       // Client-side error
//       console.error('Client-side error:', error.error.message);
//     } else {
//       // Server-side error
//       console.error(`Server-side error: ${error.status} - ${error.message}`);
//     }
//     return throwError(() => new Error('Something went wrong. Please try again later.'));
//   }
// }

// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { Pic } from './pic.model';

// @Injectable({
//   providedIn: 'root',
// })
// export class DataService {
//   private apiUrl = 'http://localhost:8080'; // Replace with your backend API URL

//   constructor(private http: HttpClient) {}

// getPics(): Observable<Pic[]> {
//   return this.http.get<Pic[]>(`${this.apiUrl}/pics`);


// }

// updatePhotoStatus(id: number, status: string): Observable<void> {
//   const url = `${this.apiUrl}/pics`;
//   const body = { id, status };
//   return this.http.put<void>(url, body);
// }


  
//   getFileUrl(fileName: string): string {
//     return `${this.apiUrl}/file/${fileName}`; // Adjust based on your backend file serving logic
//   }


//     // Delete a picture permanently
//     deletePic(id: number): Observable<{message : string}> {
//       const url = `${this.apiUrl}/pics?id=${id}`; // Use query parameter for the ID
//       return this.http.delete<{ message: string }>(url);
//         }

// }

