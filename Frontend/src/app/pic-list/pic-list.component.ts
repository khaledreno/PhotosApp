import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DataService } from '../data.service';
import { Pic } from '../pic.model';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-pic-list',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule, HttpClientModule,NavbarComponent],
  providers: [HttpClient],
  templateUrl: './pic-list.component.html',
  styleUrls: ['./pic-list.component.css'],
})
export class PicListComponent implements OnInit {
  pics: Pic[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    this.refreshPics(); // Fetch initial list of pics
  }

  getFileUrl(location: string | undefined): string {
    if (!location) return '';
    const fileName = location.split('/').pop();
    return this.dataService.getFileUrl(fileName || '');
  }

  isImage(location: string | undefined): boolean {
    if (!location) return false;
    const fileName = location.split('/').pop();
    const extension = fileName?.split('.').pop()?.toLowerCase();
    return ['jpg', 'jpeg', 'png', 'gif'].includes(extension || '');
  }

  approvePic(id: number): void {
    this.dataService.updatePhotoStatus(id, 'APPROVED').subscribe({
      next: () => {
        console.log('Pic approved:', id);
        window.location.reload(); // Refresh the entire page
      },
      error: (error) => console.error('Failed to approve pic:', error), //console log
    });
  }

  declinePic(id: number): void {
    this.dataService.updatePhotoStatus(id, 'DECLINED').subscribe({
      next: () => {
        console.log('Pic declined:', id);
        window.location.reload(); // Refresh the entire page
      },
      error: (error) => console.error('Failed to decline pic:', error),//console log
    });
  }

  // approvePic(id: number): void {
  //   this.dataService.approvePic(id,'APPROVED').subscribe({
  //     next: () => {
  //       console.log('Pic approved:', id);
  //       window.location.reload(); // Refresh the entire page
  //     },
  //     error: (error) => console.error('Failed to approve pic:', error),
  //   });
  // }

  // declinePic(id: number): void {
  //   this.dataService.declinePic(id).subscribe({
  //     next: () => {
  //       console.log('Pic declined:', id);
  //       window.location.reload(); // Refresh the entire page
  //     },
  //     error: (error) => console.error('Failed to decline pic:', error),
  //   });
  // }

  deletePic(id: number): void {
    this.dataService.deletePic(id).subscribe({
      
      next: () => {
        console.log('Pic deleted:', id);
        this.refreshPics();
      },
      error: (error) => console.error('Failed to delete pic:', error),
    });
  }

  
  refreshMethod():void{
    console.log('Refreshing the page...'); // Debugging
    window.location.reload();
  }


  refreshPics(): void {
    this.dataService.getPics().subscribe({
      next: (response) => {
        console.log('Fetched pics:', response); // Debugging
        this.pics = response; // Update the pics array with the latest data
      },
      error: (error) => console.error('Failed to fetch pics:', error),
    });
  }
}

// import { Component, OnInit } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { HttpClient, HttpClientModule } from '@angular/common/http';
// import { MatCardModule } from '@angular/material/card';
// import { MatButtonModule } from '@angular/material/button';
// import { MatIconModule } from '@angular/material/icon';
// import { DataService } from '../data.service';
// import { Pic } from '../pic.model';

// // interface Pic {
// //   id: number;
// //   uploader: string;
// //   location: string;
// //   status: string;
// //   createdAt: string;
// // }


// @Component({
//   selector: 'app-pic-list',
//   standalone: true,
//   imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule,HttpClientModule],
//   providers: [HttpClient,HttpClientModule], // Import Angular Material modules
//   templateUrl: './pic-list.component.html', // Use external template file
//   styleUrls: ['./pic-list.component.css'], // Use external styles file
// })
// export class PicListComponent implements OnInit {
//   pics: Pic[] = []; // Use the Pic interface

//   constructor(private dataService: DataService) {}

//   ngOnInit(): void {
//     this.dataService.getPics().subscribe({
//       next: (response) => {
//         this.pics = response; // Assign the fetched pics to the component property
//       },
//       error: (error) => {
//         console.error('Failed to fetch pics:', error);
//       },
//     });
//   }

//   // Method to get the file URL
//   getFileUrl(location: string | undefined): string {
//     if (!location) {
//       return ''; // Return an empty string if location is undefined or null
//     }
//     const fileName = location.split('/').pop(); // Extract the file name from the path
//     return this.dataService.getFileUrl(fileName || ''); // Ensure fileName is a string
//   }

//   // Method to check if the file is an image
//   isImage(location: string | undefined): boolean {
//     if (!location) {
//       return false; // Return false if location is undefined or null
//     }
//     const fileName = location.split('/').pop(); // Extract the file name from the path
//     const extension = fileName?.split('.').pop()?.toLowerCase(); // Extract the file extension
//     return ['jpg', 'jpeg', 'png', 'gif'].includes(extension || ''); // Ensure extension is a string
//   }

//   // Method to approve a pic
//   approvePic(id: number): void {
//     this.dataService.approvePic(id).subscribe({
//       next: () => {
//         this.pics = this.pics.map(pic => 
//           pic.id === id ? { ...pic, status: 'APPROVED' } : pic);
//           window.location.reload();      },
//       error: (error) => {
//         console.error('Failed to approve pic:', error);
//       },
//     });
//   }

//   // Method to decline a pic
//   declinePic(id: number): void {
//     this.dataService.declinePic(id).subscribe({
//       next: () => {
//         this.pics = this.pics.map(pic => 
//           pic.id === id ? { ...pic, status: 'DECLINED' } : pic);
//           window.location.reload();      },
//       error: (error) => {
//         console.error('Failed to decline pic:', error);
//       },
//     });
//   }

//   // Method to delete a pic
// deletePic(id: number): void {
//   const apiUrl = `http://localhost:8080/pics/${id}/delete`; // Replace with your Spring backend API endpoint

//   this.dataService.deletePic(id).subscribe({
//     next: () => {
//       this.pics = this.pics.filter(pic => pic.id !== id);
      
//      },
//     error: (error) => {
//       console.error('Failed to delete pic:', error);
//     }
//   });
// }

// refreshPics(): void {
//   console.log('Refreshing pics data...');
//   this.dataService.getPics().subscribe({
//     next: (response) => {
//       console.log('Pics data fetched successfully:', response);
//       this.pics = response; // Update the pics array with the latest data
//     },
//     error: (error) => {
//       console.error('Failed to fetch pics:', error);
//     },
//   });
// }
// }

