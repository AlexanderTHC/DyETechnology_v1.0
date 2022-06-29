/* ALERTS */
$(document).ready(function () {
    if ($("#success").text() !== "") {
         Swal.fire({
             icon: 'success',
             text: 'Hasta luego 👋🙂',
             backdrop: true,
             title: 'Sesión Finalizada',
             allowOutsideClick: false,
             allowEscapeKey: false,
             allowEnterKey: false,
             stopKeydownPropagation: false,
             showConfirmButton: true,
             confirmButtonColor: '#218838',
             confirmButtonText: 'Aceptar'
         });
     }
     if ($("#error").text() !== "") {
         Swal.fire({
             icon: 'error',
             title: '¡Error 😵!',
             text: 'Ingrese un Usuario y/o Contraseña que sea valido',
             backdrop: true,
             allowOutsideClick: false,
             allowEscapeKey: false,
             allowEnterKey: false,
             stopKeydownPropagation: false,
             showConfirmButton: true,
             confirmButtonColor: '#dc3545',
             confirmButtonText: 'Aceptar'
         });
     }
 });