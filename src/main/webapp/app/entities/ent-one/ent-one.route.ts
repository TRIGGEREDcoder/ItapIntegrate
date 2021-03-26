import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEntOne, EntOne } from 'app/shared/model/ent-one.model';
import { EntOneService } from './ent-one.service';
import { EntOneComponent } from './ent-one.component';
import { EntOneDetailComponent } from './ent-one-detail.component';
import { EntOneUpdateComponent } from './ent-one-update.component';

@Injectable({ providedIn: 'root' })
export class EntOneResolve implements Resolve<IEntOne> {
  constructor(private service: EntOneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntOne> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((entOne: HttpResponse<EntOne>) => {
          if (entOne.body) {
            return of(entOne.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntOne());
  }
}

export const entOneRoute: Routes = [
  {
    path: '',
    component: EntOneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EntOnes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EntOneDetailComponent,
    resolve: {
      entOne: EntOneResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EntOnes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EntOneUpdateComponent,
    resolve: {
      entOne: EntOneResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EntOnes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EntOneUpdateComponent,
    resolve: {
      entOne: EntOneResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EntOnes'
    },
    canActivate: [UserRouteAccessService]
  }
];
