import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntOne } from 'app/shared/model/ent-one.model';

@Component({
  selector: 'cg-ent-one-detail',
  templateUrl: './ent-one-detail.component.html'
})
export class EntOneDetailComponent implements OnInit {
  entOne: IEntOne | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entOne }) => (this.entOne = entOne));
  }

  previousState(): void {
    window.history.back();
  }
}
