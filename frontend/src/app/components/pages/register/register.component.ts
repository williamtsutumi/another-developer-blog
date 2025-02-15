import { Component, inject } from '@angular/core';
import { LoginInputComponent } from '../../login-input/login-input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginLayoutComponent } from '../../login-layout/login-layout.component';
import { Title } from '@angular/platform-browser';
import { LoginService } from '../../../services/login.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';


interface LoginForm {
  name: FormControl,
  email: FormControl,
  password: FormControl
}

@Component({
  selector: 'app-register',
  imports: [
    LoginLayoutComponent,
    LoginInputComponent,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  private loginService = inject(LoginService);
  private toastService = inject(ToastrService);
  registerForm!: FormGroup<LoginForm>;

  emailLabel: string | null = null;

  constructor(
    private titleService: Title,
    private router: Router
  ) {
    this.titleService.setTitle("Cadastro");
    this.registerForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  ngOnInit() {
    this.registerForm.controls['email'].valueChanges.subscribe(() => {
      if (!this.registerForm.get('email')?.valid) {
        this.emailLabel = "inválido"
      }
      else {
        this.emailLabel = null;
      }
    });
  }

  onSubmit() {
    this.loginService.register(
      this.registerForm.value.name,
      this.registerForm.value.email,
      this.registerForm.value.password
    ).subscribe({
      next: () => {
        this.toastService.success("Cadastro feito com sucesso! Você já pode fazer login");
        this.router.navigate(['/login']);
      },
      error: (error) => {
        if (typeof error.error === 'string') {
          this.toastService.error(error.error);
        }
        else {
          this.toastService.error("Erro inesperado! Tente novamente mais tarde");
        }
      }
    });
  }
}
