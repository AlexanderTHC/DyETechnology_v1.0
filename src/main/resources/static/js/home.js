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
 });