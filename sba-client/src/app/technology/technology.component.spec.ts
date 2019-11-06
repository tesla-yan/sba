import { DebugElement, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TechnologyComponent } from './technology.component';
import { ComponentFixture, async, TestBed, inject } from '@angular/core/testing';
import { BrowserModule, By } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient, HttpRequest } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthToken } from '../models/authToken';
import { AppComponent } from '../app.component';
import { HomeComponent } from '../home/home.component';


describe('TechnologyComponent', () => {
  let component: TechnologyComponent;
  let fixture: ComponentFixture<TechnologyComponent>;
  let de: DebugElement;
  let el: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        TechnologyComponent
      ],
      imports: [
        AppComponent,
        HomeComponent,
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientTestingModule,
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    }).compileComponents().then(() => {
      fixture = TestBed.createComponent(TechnologyComponent);
      component = fixture.componentInstance; // TechnologyComponent test instance      
      const logedUser: AuthToken = { id: 1, userName: "admin", role: "ADMIN", token: "testToken" };
      localStorage.setItem('logedUser', JSON.stringify(logedUser));
      component.pagination = {
        content: [], totalPages: 0, totalElements: 0, last: false, size: 0, first: true, sort: "", numberOfElements: 0
      };
      fixture.detectChanges();
    });
  }));

 
 


 


});
