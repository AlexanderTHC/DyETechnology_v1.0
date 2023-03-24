$(document).ready(function(){
    $('.articulosView').slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
      });
  });

  /* ALERTS */
$(document).ready(function () {
    if ($("#errprUsuario").text() !== "") {
        Swal.fire({
            icon: 'error',
            title: '¡Error!',
            text: 'Faltan Datos para confirmar la Compra del Producto, Revisa tus datos en "Mi Perfil"',
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
    if ($("#sesionFinalizada").text() !== "") {
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
     if ($("#sesionIniciada").text() !== "") {
        Swal.fire({
            icon: 'success',
            text: '¡Bienvenido!',
            backdrop: true,
            title: 'Sesión iniciada con éxito',
            allowOutsideClick: false,
            allowEscapeKey: false,
            allowEnterKey: false,
            stopKeydownPropagation: false,
            showConfirmButton: true,
            confirmButtonColor: '#218838',
            confirmButtonText: 'Aceptar'
        });
    }
    //* CAMBIAR LUEGO PARA QUE SEA CON TEMPORIZADOR, PARA NO PRESIONAR EL BOTON ACEPTAR.
    if ($("#ordenGenerada").text() !== "") {
        Swal.fire({
            icon: 'success',
            text: 'La orden se genero con éxito',
            backdrop: true,
            title: '¡Éxito!',
            allowOutsideClick: false,
            allowEscapeKey: false,
            allowEnterKey: false,
            stopKeydownPropagation: false,
            showConfirmButton: true,
            confirmButtonColor: '#218838',
            confirmButtonText: 'Aceptar'
        });
    }
    if ($("#errorCarrito").text() !== "") {
        Swal.fire({
            icon: 'error',
            text: 'Debera iniciar sesión para poder agregar productos al carrito.',
            backdrop: true,
            title: '¡Error!',
            allowOutsideClick: false,
            allowEscapeKey: false,
            allowEnterKey: false,
            stopKeydownPropagation: false,
            showConfirmButton: true,
            showCancelButton: true,
            confirmButtonColor: '#218838',
            confirmButtonText: 'Iniciar Sesión',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/login";
            }
       /* }).then((result) => {
            if (result.isCancelled) {
                location.href = "/home";
            }*/
        });
    }
 });