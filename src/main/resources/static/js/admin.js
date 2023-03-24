$(document).ready(function () {
    if ($("#warning").text() !== "") {
        Swal.fire({
            icon: 'warning',
            title: '¡Advertencia!',
            text: 'Se modificó con éxito la Orden del Cliente.',
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

$(document).ready(function () {
    $('.counter-value').each(function () {
        $(this).prop('Counter', 0).animate({
            Counter: $(this).text()
        }, {
            duration: 3500,
            easing: 'swing',
            step: function (now) {
                $(this).text(Math.ceil(now));
            }
        });
    });
});

//TABLA ORDENES
$(document).ready(function () {
    $('#tablaOrdenes').DataTable({
        lengthMenu: [20, 40, 60, 80, 100],

        language: {
            "search": "Buscar por: ",
            "lengthMenu": "Mostrar _MENU_ registros",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
            "zeroRecords": "No hay registros",
            "infoEmpty": "No hay registros",
            "infoFiltered": "(Encontrados _MAX_ de registros)",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });

    $('.money').mask('#.##0,00', { reverse: true });
});

$(document).ready(function () {
    let estado = $("#estado").val();
    console.log("Estado: " + estado);
    const classEstado = document.querySelector('#estadoText');
    

    if (estado == "Aceptado") {
        classEstado.className = 'fw-bold text-success';
    } else if (estado == "Cancelado") {
        classEstado.className = 'fw-bold text-danger';
    } else {
        classEstado.className = 'fw-bold text-warning';
    }

});