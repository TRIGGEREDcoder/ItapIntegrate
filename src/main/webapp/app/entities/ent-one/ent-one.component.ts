import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntOne } from 'app/shared/model/ent-one.model';
import { EntOneService } from './ent-one.service';
import { EntOneDeleteDialogComponent } from './ent-one-delete-dialog.component';

@Component({
  selector: 'cg-ent-one',
  templateUrl: './ent-one.component.html'
})
export class EntOneComponent implements OnInit, OnDestroy {
  entOnes?: IEntOne[];
  eventSubscriber?: Subscription;

  constructor(protected entOneService: EntOneService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.entOneService.query().subscribe((res: HttpResponse<IEntOne[]>) => (this.entOnes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEntOnes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEntOne): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEntOnes(): void {
    this.eventSubscriber = this.eventManager.subscribe('entOneListModification', () => this.loadAll());
  }

  delete(entOne: IEntOne): void {
    const modalRef = this.modalService.open(EntOneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entOne = entOne;
  }
}
