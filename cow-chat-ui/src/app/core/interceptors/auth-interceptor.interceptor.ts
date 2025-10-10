import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const token = localStorage.getItem('auth_token');

  const clonedReq = token
    ? req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      })
    : req;

  return next(clonedReq).pipe(
    catchError(err => {
      if (err.status === 401) {
        console.warn('Token inválido o expirado');
        router.navigate(['/login']);
      }

      return throwError(() => err);
    })
  );
};
