const {createApp} = Vue;

createApp({
	data() {
		return {
			rol: '',
			Courses:"",
			correo: '',
			correoRegistro: '',
			contraseña: '',
			contraseñaRegistro: '',
			Nombre: '',
			Apellido: '',
			telefono: '',
		};
	},
	created() {
		this.data();
		this.Courses();
	},
	mounted() {
		this.roles();
	},
	methods: {
		Courses() {
			axios
				.get('/api/courses')
				.then(response => {
					this.productos = response.data;
				})
				.catch(error => console.log(error));
		},
		data() {
			axios
				.get('/api/clientes/actual')
				.then(response => {
					this.datos = response.data;
					this.clienteIngresado = response.data;
					this.verificado = response.data.verificado === true;
				})
				.catch(error => console.log(error));
		},
		roles() {
			axios
				.get('/api/clientes/actual/rol')
				.then(response => {
					this.rol = response.data;
				})
				.catch(error => {
					console.log(error);
				});
		},
		ingresar() {
			axios
				.post('/api/login', 'correo=' + this.correo + '&contraseña=' + this.contraseña)
				.then(response => {
					Swal.fire({
						icon: 'success',
						text: 'Ingreso Exitoso',
						showConfirmButton: false,
						timer: 2000,
					}).then(() => {
						if (this.correo == 'admin@gmail.com') {
							window.location.replace('/index.html');
						} else {
							window.location.replace('/index.html');
						}
					});
				})
				.catch(error =>
					Swal.fire({
						icon: 'error',
						text: error.response.data,
						confirmButtonColor: '#7c601893',
					})
				);
		},
		register() {
			axios
				.post(
					'/api/clientes',
					'primerNombre=' +
						this.primerNombre +
						'&segundoNombre=' +
						this.segundoNombre +
						'&primerApellido=' +
						this.primerApellido +
						'&segundoApellido=' +
						this.segundoApellido +
						'&telefono=' +
						this.telefono +
						'&correo=' +
						this.correoRegistro +
						'&contraseña=' +
						this.contraseñaRegistro
				)
				.then(response => {
					Swal.fire({
						icon: 'success',
						text: 'Se envio a tu correo la validacion',
						showConfirmButton: false,
						timer: 2000,
					}).then(() => {
						this.correo = this.correoRegistro;
						this.contraseña = this.contraseñaRegistro;
						window.location.replace('/index.html');
						// this.ingresar();
					});
				})
				.catch(error =>
					Swal.fire({
						icon: 'error',
						text: error.response.data,
						confirmButtonColor: '#7c601893',
					})
				);
		},
		salir() {
			Swal.fire({
				title: '¿Estas seguro que quieres salir de tu cuenta?',
				inputAttributes: {
					autocapitalize: 'off',
				},
				showCancelButton: true,
				confirmButtonText: 'Sure',
				showLoaderOnConfirm: true,
				preConfirm: login => {
					return axios
						.post('/api/logout')
						.then(response => {
							this.carrito = []
							window.location.href = '/index.html';
						})
						.catch(error =>
							Swal.fire({
								icon: 'error',
								text: error.response.data,
								confirmButtonColor: '#7c601893',
							})
						);
				},
				allowOutsideClick: () => !Swal.isLoading(),
			});
		},
	},
	
}).mount('#app');

window.addEventListener('load', () => {
	const loader = document.querySelector('.loader');

	loader.classList.add('loader-hidden');

	loader.addEventListener('transitioned', () => {
		document.body.removeChild('loader');
	});
});

/* Contraseña */
const pwShowHide = document.querySelectorAll('.pw-hide');
pwShowHide.forEach(icon => {
	icon.addEventListener('click', () => {
		let getPwInput = icon.parentElement.querySelector('input');
		if (getPwInput.type === 'password') {
			getPwInput.type = 'text';
			icon.classList.replace('fa-eye-slash', 'fa-eye');
		} else {
			getPwInput.type = 'password';
			icon.classList.replace('fa-eye', 'fa-eye-slash');
		}
	});
});
