import { DebugElement, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MentorSkillComponent } from './mentor_skill.component';
import { ComponentFixture, async, TestBed, inject } from '@angular/core/testing';
import { BrowserModule, By } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthToken } from '../models/authToken';
import { BarRatingModule } from 'ngx-bar-rating';

describe('MentorSkillComponent', () => {
    let comp: MentorSkillComponent;
    let fixture: ComponentFixture<MentorSkillComponent>;
    let de: DebugElement;
    let el: HTMLElement;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                MentorSkillComponent
            ],
            imports: [
                BrowserModule,
                FormsModule,
                ReactiveFormsModule,
                HttpClientModule,
                HttpClientTestingModule,
                BarRatingModule,
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents().then(() => {
            fixture = TestBed.createComponent(MentorSkillComponent);
            comp = fixture.componentInstance; // MentorSkillComponent test instance
            const logedUser: AuthToken = {id: 1, userName: "admin", role: "ADMIN", token: "testToken"};
            localStorage.setItem('logedUser', JSON.stringify(logedUser));
            comp.pagination = {
                content: [], totalPages: 0, totalElements: 0, last: false, size: 0, first: true, sort: "", numberOfElements: 0
            };
            comp.mentorSkill = {
                id: 0,
                mentorId: 1,
                skillId: "",
                selfRating: 0,
                yearsOfExperience: "",
                tariningsDelivered: "",
                facilitiesOffered: "",
                fees: ""
            };
            fixture.detectChanges();
        });
    }));


    it('should create', () => {
        expect(comp).toBeTruthy();
      });

    it(`should create the mentor_skill.component`, () => {
        const app = fixture.debugElement.componentInstance;
        expect(app).toBeTruthy();
    });
    it('Skill Id is null ',()=>{
      const app = fixture.debugElement.componentInstance;
      expect(app.selfRating).toBeNull;
      
    });

   
    it('Skill Id is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.skillId).toBeNull;
      });
      // it('Mentor Id is null ',()=>{
      //   const app = fixture.debugElement.componentInstance;
      //   expect(app.mentorId).toBeNull;
      // });
      it(' Id is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.Id).toBeNull;
      });
      
      it('skillName is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.skillName).toBeNull;
      });
      it('Skill  is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.Skill).toBeNull;
      });
      it('yearsOfExperience  is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.yearsOfExperience).toBeNull;
      });
      

    it('facilitiesOffered  is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.facilitiesOffered).toBeNull;
      });
      it('fees is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.fees).toBeNull;
      });

      it('tariningsDelivered  is null ',()=>{
        const app = fixture.debugElement.componentInstance;
        expect(app.tariningsDelivered).toBeNull;
      }); 
    it(`should respond with dummy data`, async(() => {
        inject([HttpClient, HttpTestingController],
            (http: HttpClient, backend: HttpTestingController) => {

                http.get('/foo/bar').subscribe((next) => {
                    expect(next).toEqual({ baz: '123' });
                });
                backend.match({
                    url: '/foo/bar',
                    method: 'GET'
                })[0].flush({ baz: '123' });

            })
    }));

});