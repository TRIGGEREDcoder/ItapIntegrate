import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntOne } from 'app/shared/model/ent-one.model';
import { EntOneService } from './ent-one.service';

@Component({
  templateUrl: './ent-one-delete-dialog.component.html'
})
export class EntOneDeleteDialogComponent {
  entOne?: IEntOne;

  constructor(protected entOneService: EntOneService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.entOneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('entOneListModification');
      this.activeModal.close();
    });
  }
}
