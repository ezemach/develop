const {createApp} = Vue;

createApp({
	data() {
		return {
			rol:'',
			newUser:{
                ssn:'',	
                name:'',
                lastName:'',
                email:'',
                phone:'',
                birthday:'',
                state:'',
                city:'',
                address:'',
                password:'',
                },
            search: '',
			passwordLogin:'',
			emailLogin:'',
			cursoPorId:'',
			imgCursoPorId:'',
            coursesFilter:""
			
		};
	},
	created() {
		this.data();
		this.Mcourses();
	},
	mounted() {
		this.roles();
	},
	methods: {
		Mcourses() {
			axios
				.get('/api/courses')
				.then(response => {
					this.courses = response.data;
                    this.coursesFilter = this.courses
					console.log(this.courses)
				})
				.catch(error => console.log(error));
		},
		data() {
			axios
				.get('/api/users')
				.then(response => {
					this.datos = response.data;
				})
				.catch(error => console.log(error));
		},
		obtenerIdCurso(id) {
			axios
				.get('/api/courses/' + id)
				.then(response => {
					this.cursoPorId = response.data;
					console.log(this.cursoPorId)
					this.imgCursoPorId = this.cursoPorId.imageUrl;
				})
				.catch(error => console.log(error))},
		ingresar() {
			axios
				.post('/api/login', 'email=' + this.emailLogin + '&password=' + this.passwordLogin)
				.then(response => {
					Swal.fire({
						icon: 'success',
						text: 'Ingreso Exitoso',
						showConfirmButton: false,
						timer: 2000,
					}).then(() => {
						if (this.email == 'admin@admin.com') {
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
		roles() {
			axios
				.get('/api/users/current/rol')
				.then(response => {
					this.rol = response.data;
				})
				.catch(error => {
					console.log(error);
				});
		},
		register() {
			axios
				.post(
					'/api/users',this.newUser	
				)
				.then(response => {
					Swal.fire({
						icon: 'success',
						text: 'Registrado con Exito',
						showConfirmButton: false,
						timer: 2000,
					}).then(() => {

						window.location.replace('/index.html');
					});
				})
				.catch(error =>
					console.log(error),
					Swal.fire({
						icon: 'error',
						text: error.response.data,
						confirmButtonColor: '#7c601893',
					})
				);
		},
		salir() {
			Swal.fire({
				title: '¿Are you Sure that you log out?',
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
		filterCourse() {
			this.coursesFilter= this.courses.filter(course => {
				return course.name.toLowerCase().includes(this.search.toLowerCase());
			});
		},
}
}).mount('#app');

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
