import { Component, OnDestroy } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { Subscription } from 'rxjs';
import { AppService } from './app.service';
import { authConfig } from "./auth.config";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  title = 'frontend';
  text = 'logged out';
  helloSubscription: Subscription;

  constructor(private oauthService: OAuthService, private appService: AppService) {
    this.configure();
    this.helloSubscription = this.appService.hello().subscribe(response => {
      this.text = response;
    });
  }
  ngOnDestroy(): void {
    this.helloSubscription.unsubscribe();
  }

  private configure(): void {
    this.oauthService.configure(authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  login() {
    this.oauthService.initCodeFlow();
  }

  logout() {
    this.oauthService.logOut();
  }
}
