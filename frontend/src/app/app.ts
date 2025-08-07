import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LabseqService } from './services/labseq';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  n: number = 0;
  result: string = '';
  loading: boolean = false;
  error: string = '';

  constructor(private labseqService: LabseqService) {}

  calculateSequence() {
    if (this.n < 0) {
      this.error = 'Please enter a non-negative integer';
      return;
    }

    this.loading = true;
    this.error = '';
    this.result = '';
    
    this.labseqService.getSequenceValue(this.n).subscribe({
      next: (data) => {
        this.result = data.toString();
        this.loading = false;
      },
      error: (err) => {
        this.error = err.error || 'An error occurred while calculating the sequence';
        this.loading = false;
      }
    });
  }
}