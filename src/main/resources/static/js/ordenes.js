// TABLA PRODUCTOS
$(document).ready(function () {
    $('#tablaMisOrdenes').DataTable({
        lengthMenu: [5, 10, 15, 20],

        language: {
            "search": "Buscar: ",
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
    
    $('.money').mask('#.##0,00', {reverse: true});
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