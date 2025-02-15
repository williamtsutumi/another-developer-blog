import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';


export const AuthInterceptor: HttpInterceptorFn = (req, next) => {

    const authToken = sessionStorage.getItem('auth-token');
    const clonedRequest = req.clone({
        setHeaders: {
            Authorization: "Bearer " + authToken,
        },
    });

    return next(clonedRequest).pipe(
        catchError((error) => {
            console.log("Ocorreu um problema no interceptor.")
            return throwError(() => error);
        })
    );
};