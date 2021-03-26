import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ent-one',
        loadChildren: () => import('./ent-one/ent-one.module').then(m => m.ItapIntegrationEntOneModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ItapIntegrationEntityModule {}
