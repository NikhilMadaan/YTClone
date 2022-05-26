import {NgModule} from '@angular/core';
import {AuthModule} from 'angular-auth-oidc-client';


@NgModule({
  imports: [AuthModule.forRoot({
    config: {
            authority: 'https://dev-3cm2yz0q.us.auth0.com',
      redirectUrl: window.location.origin,
            clientId: 'kdjgMQGqOscmGSv1q7tp5IctfTEfqFPb',
      scope: 'openid profile offline_access',
      responseType: 'code',
      silentRenew: true,
      useRefreshToken: true,
      secureRoutes: ['http://localhost:8080/'],
      customParamsAuthRequest: {
        audience: 'http://localhost:8080/'
      }
    }
  })],
  providers: [],
  exports: [AuthModule],
})
export class AuthConfigModule {
}
