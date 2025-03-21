import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DataService } from '../../data.service';
import { Pic } from '../../pic.model';

@Component({
  selector: 'app-pic-list',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule, HttpClientModule],
  providers: [HttpClient],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  pics: Pic[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    this.dataService.ShowPics().subscribe({
      next: (data) => {
        this.pics = data;
        console.log('Pics loaded:', this.pics);
      },
      error: (error) => console.error('Failed to load pics:', error)
    });
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


  
  refreshMethod():void{
    console.log('Refreshing the page...'); // Debugging
    window.location.reload();
  }




}

