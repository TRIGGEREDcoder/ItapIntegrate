import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ItapIntegrationSharedModule } from 'app/shared/shared.module';
import { ItapIntegrationCoreModule } from 'app/core/core.module';
import { ItapIntegrationAppRoutingModule } from './app-routing.module';
import { ItapIntegrationHomeModule } from './home/home.module';
import { ItapIntegrationEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    ItapIntegrationSharedModule,
    ItapIntegrationCoreModule,
    ItapIntegrationHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ItapIntegrationEntityModule,
    ItapIntegrationAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class ItapIntegrationAppModule {}
