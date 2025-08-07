import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LabseqService {
  private apiUrl = 'http://localhost:8080/labseq';

  constructor(private http: HttpClient) { }

  getSequenceValue(n: number): Observable<string> {
    return this.http.get(`${this.apiUrl}/${n}`, { responseType: 'text' });
  }
}