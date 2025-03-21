import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { UploadService } from '../services/upload.service';

@Component({
  selector: 'app-upload',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule
  ],
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [UploadService]
})
export class UploadComponent {
  uploadedFiles: File[] = [];
  isDragging: boolean = false; // Track drag-over state

  constructor(private uploadService: UploadService) {}

  // Method to handle file selection via input
  onFileSelected(event: any): void {
    this.addFiles(event.target.files);
  }

  // Method to handle file drop
  onDrop(event: DragEvent): void {
    event.preventDefault();
    this.isDragging = false; // Reset drag-over state
    if (event.dataTransfer?.files) {
      this.addFiles(event.dataTransfer.files);
    }
  }

  // Method to handle drag over event
  onDragOver(event: DragEvent): void {
    event.preventDefault();
    this.isDragging = true; // Set drag-over state
  }

  // Method to handle drag leave event
  onDragLeave(event: DragEvent): void {
    event.preventDefault();
    this.isDragging = false; // Reset drag-over state
  }

  // Add files to the uploadedFiles array
  addFiles(files: FileList): void {
    for (let i = 0; i < files.length; i++) {
      this.uploadedFiles.push(files[i]);
    }
  }

  // Method to upload files
  uploadFiles(): void {
    if (this.uploadedFiles.length === 0) {
      console.error('No files to upload');
      return;
    }

    const uploadedBy = 'khaled'; // Replace with dynamic value if needed

    this.uploadedFiles.forEach(file => {
      this.uploadService.uploadPhoto(file, uploadedBy).subscribe({
        next: (response) => {
          console.log('Upload successful', response);
        },
        error: (err) => {
          console.error('Upload failed', err);
        },
        complete: () => {
          console.log(`Finished uploading ${file.name}`);
        }
      });
    });

    this.uploadedFiles = [];
  }
}