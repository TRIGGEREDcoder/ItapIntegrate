import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ItapIntegrationTestModule } from '../../../test.module';
import { EntOneDetailComponent } from 'app/entities/ent-one/ent-one-detail.component';
import { EntOne } from 'app/shared/model/ent-one.model';

describe('Component Tests', () => {
  describe('EntOne Management Detail Component', () => {
    let comp: EntOneDetailComponent;
    let fixture: ComponentFixture<EntOneDetailComponent>;
    const route = ({ data: of({ entOne: new EntOne(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ItapIntegrationTestModule],
        declarations: [EntOneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EntOneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntOneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entOne on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entOne).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
