import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItapIntegrationTestModule } from '../../../test.module';
import { EntOneComponent } from 'app/entities/ent-one/ent-one.component';
import { EntOneService } from 'app/entities/ent-one/ent-one.service';
import { EntOne } from 'app/shared/model/ent-one.model';

describe('Component Tests', () => {
  describe('EntOne Management Component', () => {
    let comp: EntOneComponent;
    let fixture: ComponentFixture<EntOneComponent>;
    let service: EntOneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ItapIntegrationTestModule],
        declarations: [EntOneComponent]
      })
        .overrideTemplate(EntOneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntOneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntOneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntOne(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entOnes && comp.entOnes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
