const {createApp} = Vue;

createApp({
	data() {
		return {
			rol:'',
			passwordLogin:'',
			emailLogin:'',
			cursoPorId:'',
			imgCursoPorId:'',
            users:'',
            onlyStudents:'',
            onlyTeachers:'',
            coursesFilter:''			
		};
	},
	created() {
		this.data();
		this.Mcourses();
        this.student();
        this.teacher();
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
        student(){
            axios
				.get('/api/users')
				.then(response => {
					this.users = response.data
					console.log(this.users)
                    this.onlyStudents = this.users.filter(studentUser =>{
                        if (!studentUser.email.endsWith('@edu.com') && !studentUser.email.endsWith('@admin.com') ) {
                            return true
                        }
                     })
                     console.log(this.onlyStudents)
				})
				.catch(error => console.log(error));
        },
        teacher(){
            axios
				.get('/api/users')
				.then(response => {
					this.users = response.data
					console.log(this.users)
                    this.onlyTeachers = this.users.filter(teacherUser =>{
                        if (teacherUser.email.endsWith('@edu.com')) {
                            return true
                        }
                     })
                     console.log(this.onlyTeachers)
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
        roles() {
			axios
				.get('/api/users/current/rol')
				.then(response => {
					this.rol = response.data;
				})
				.catch(error => {
					console.log(error);
				})},
		obtenerIdCurso(id) {
			axios
				.get('/api/courses/' + id)
				.then(response => {
					this.cursoPorId = response.data;
					console.log(this.cursoPorId)
					this.imgCursoPorId = this.cursoPorId.imageUrl;
				})
				.catch(error => console.log(error))},
        deleteUser(id) {
                    Swal.fire({
                        title: 'Are you sure to delete this user?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Delete',
                    }).then(result => {
                        if (result.isConfirmed) {
                            axios
                                .delete(`/api/users/${id}`)
                                .then(response => {
                                    Swal.fire({
                                        icon: 'success',
                                        text: 'Succesfull Delete',
                                        showConfirmButton: false,
                                        timer: 2000,
                                    }).then(() => (window.location.href = '/manager.html'));
                                })
                                .catch(error => {
                                    Swal.fire({
                                        icon: 'error',
                                        text: error.response.data,
                                        confirmButtonColor: '#7c601893',
                                    });
                                });
                        }
                    });
                },
                deleteCourse(id) {
                    Swal.fire({
                        title: 'Are you sure to delete this course?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Delete',
                    }).then(result => {
                        if (result.isConfirmed) {
                            axios
                                .delete(`/api/courses/${id}`)
                                .then(response => {
                                    Swal.fire({
                                        icon: 'success',
                                        text: 'Succesfull Delete',
                                        showConfirmButton: false,
                                        timer: 2000,
                                    }).then(() => (window.location.href = '/manager.html'));
                                })
                                .catch(error => {
                                    Swal.fire({
                                        icon: 'error',
                                        text: error.response.data,
                                        confirmButtonColor: '#7c601893',
                                    });
                                });
                        }
                    });
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
		
}
}).mount('#app');



