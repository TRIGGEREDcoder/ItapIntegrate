import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntOne, EntOne } from 'app/shared/model/ent-one.model';
import { EntOneService } from './ent-one.service';

@Component({
  selector: 'cg-ent-one-update',
  templateUrl: './ent-one-update.component.html'
})
export class EntOneUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    fieldOne: []
  });

  constructor(protected entOneService: EntOneService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entOne }) => {
      this.updateForm(entOne);
    });
  }

  updateForm(entOne: IEntOne): void {
    this.editForm.patchValue({
      id: entOne.id,
      fieldOne: entOne.fieldOne
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entOne = this.createFromForm();
    if (entOne.id !== undefined) {
      this.subscribeToSaveResponse(this.entOneService.update(entOne));
    } else {
      this.subscribeToSaveResponse(this.entOneService.create(entOne));
    }
  }

  private createFromForm(): IEntOne {
    return {
      ...new EntOne(),
      id: this.editForm.get(['id'])!.value,
      fieldOne: this.editForm.get(['fieldOne'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntOne>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
