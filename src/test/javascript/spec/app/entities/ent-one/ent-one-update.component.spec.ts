import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ItapIntegrationTestModule } from '../../../test.module';
import { EntOneUpdateComponent } from 'app/entities/ent-one/ent-one-update.component';
import { EntOneService } from 'app/entities/ent-one/ent-one.service';
import { EntOne } from 'app/shared/model/ent-one.model';

describe('Component Tests', () => {
  describe('EntOne Management Update Component', () => {
    let comp: EntOneUpdateComponent;
    let fixture: ComponentFixture<EntOneUpdateComponent>;
    let service: EntOneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ItapIntegrationTestModule],
        declarations: [EntOneUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EntOneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntOneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntOneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntOne(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntOne();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
