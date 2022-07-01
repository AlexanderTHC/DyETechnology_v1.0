// TABLA CATEGORIAS
$(document).ready(function () {
    $('#tablaCategorias').DataTable({
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

/*************************************  METODO PARA BORRAR POR AJAX *************************************/
function borrar(id) {
    //	console.log(id);
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });
        swal.fire({
            title: '¡Advertencia!',
          text: "¿Quieres eliminar esta Categoria?, No podras revertir la acción",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#218838',
            confirmButtonText: 'Eliminar',
            cancelButtonColor: '#dc3545',
            cancelButtonText: 'Cancelar',
            reverseButtons: true,
            padding: '1rem',
            width: '31%',
            backdrop: true,
            allowOutsideClick: false,
            allowEscapeKey: false,
            allowEnterKey: false,
            stopKeydownPropagation: false,
            showConfirmButton: true
        })
                .then((result) => {
    
                    if (result.isConfirmed) {
                        $.ajax({
                            url: "/categorias/activo/" + id,
                            success: function (res) {
                                console.log(res);
                            },
                        });
                        swal.fire({
                            title: '¡Éxito!',
                          text: "Categoria eliminada",
                            icon: 'success',
                            confirmButtonColor: '#218838',
                            confirmButtonText: 'Aceptar',
                            reverseButtons: true,
                            padding: '1rem',
                            width: '31%',
                            backdrop: true,
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                            allowEnterKey: false,
                            stopKeydownPropagation: false,
                            showConfirmButton: true
                        })
                                .then((result) => {
                                    if (result.isConfirmed) {
                                        location.href = "/categorias/listado";
                                    }
                                });
                    } else if (
                            /* Read more about handling dismissals below */
                            result.dismiss === Swal.DismissReason.cancel
                            ) {
                        swal.fire({
                            title: 'Cancelado',
                          text: "No se eliminó el Categoria",
                            icon: 'error',
                            confirmButtonColor: '#dc3545',
                            confirmButtonText: 'Aceptar',
                            reverseButtons: true,
                            padding: '1rem',
                            width: '31%',
                            backdrop: true,
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                            allowEnterKey: false,
                            stopKeydownPropagation: false,
    //                        showConfirmButton: true
                        });
                    }
                });
    }