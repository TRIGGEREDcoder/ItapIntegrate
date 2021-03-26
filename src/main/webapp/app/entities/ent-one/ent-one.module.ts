import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItapIntegrationSharedModule } from 'app/shared/shared.module';
import { EntOneComponent } from './ent-one.component';
import { EntOneDetailComponent } from './ent-one-detail.component';
import { EntOneUpdateComponent } from './ent-one-update.component';
import { EntOneDeleteDialogComponent } from './ent-one-delete-dialog.component';
import { entOneRoute } from './ent-one.route';

@NgModule({
  imports: [ItapIntegrationSharedModule, RouterModule.forChild(entOneRoute)],
  declarations: [EntOneComponent, EntOneDetailComponent, EntOneUpdateComponent, EntOneDeleteDialogComponent],
  entryComponents: [EntOneDeleteDialogComponent]
})
export class ItapIntegrationEntOneModule {}
