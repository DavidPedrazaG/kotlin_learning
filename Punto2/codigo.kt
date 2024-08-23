import java.time.LocalDate

open class Persona(
    val nombreCompleto: String,
    val documentoIdentidad: String,
    val sexo: String,
    val correoElectronico: String
) {
    override fun toString(): String {
        return "Nombre: $nombreCompleto, Documento: $documentoIdentidad, Sexo: $sexo, Correo: $correoElectronico"
    }
}

class Empleado(
    nombreCompleto: String,
    documentoIdentidad: String,
    sexo: String,
    correoElectronico: String,
    val salario: Double,
    val dependencia: Dependencia,
    val anioIngreso: Int,
    val subordinados: MutableList<Empleado> = mutableListOf(),
    val cargo: Cargo
) : Persona(nombreCompleto, documentoIdentidad, sexo, correoElectronico) {
    override fun toString(): String {
        return "${super.toString()}, Salario: $salario, Dependencia: ${dependencia.nombre}, Año de Ingreso: $anioIngreso, Cargo: ${cargo.nombre}, Nivel: ${cargo.nivelJerarquico}"
    }
}

class Cliente(
    nombreCompleto: String,
    documentoIdentidad: String,
    sexo: String,
    correoElectronico: String,
    val direccionCorrespondencia: String,
    val telefono: String
) : Persona(nombreCompleto, documentoIdentidad, sexo, correoElectronico) {
    override fun toString(): String {
        return "${super.toString()}, Dirección: $direccionCorrespondencia, Teléfono: $telefono"
    }
}

data class Cargo(
    val nombre: String,
    val nivelJerarquico: Int
)

data class Dependencia(
    val nombre: String
)

class Empresa(
    val razonSocial: String,
    val nit: String,
    val direccion: String,
    val empleados: MutableList<Empleado> = mutableListOf(),
    val clientes: MutableList<Cliente> = mutableListOf()
) {
    private var empleadoAgregarUsado = false
    private var clienteAgregarUsado = false

    fun agregarEmpleado() {
        print("\nIngrese nombre completo del empleado: ")
        val nombreCompleto = readln()
        print("\nIngrese documento de identidad del empleado: ")
        val documentoIdentidad = readln()

        if (empleados.any { it.documentoIdentidad == documentoIdentidad }) {
            println("\nYa existe un empleado con ese documento de identidad.")
            return
        }

        print("\nIngrese sexo del empleado (M/F): ")
        val sexo = readln()
        print("\nIngrese correo electrónico del empleado: ")
        val correoElectronico = readln()
        print("\nIngrese salario del empleado: ")
        val salario = readln().toDouble()
        print("\nIngrese nombre de la dependencia del empleado: ")
        val nombreDependencia = readln()
        val dependencia = Dependencia(nombreDependencia)
        print("\nIngrese año de ingreso del empleado: ")
        val anioIngreso = readln().toInt()
        print("\nIngrese nombre del cargo del empleado: ")
        val nombreCargo = readln()
        print("\nIngrese nivel jerárquico del cargo del empleado: ")
        val nivelJerarquico = readln().toInt()
        val cargo = Cargo(nombreCargo, nivelJerarquico)
        
        val empleado = Empleado(nombreCompleto, documentoIdentidad, sexo, correoElectronico, salario, dependencia, anioIngreso, cargo = cargo)
        empleados.add(empleado)
        println("\nEmpleado agregado exitosamente.")
        empleadoAgregarUsado = true
    }

    fun eliminarEmpleado() {
        print("\nIngrese documento de identidad del empleado a eliminar: ")
        val documentoIdentidad = readln()
        val empleadoExistente = empleados.find { it.documentoIdentidad == documentoIdentidad }
        if (empleadoExistente != null) {
            empleados.forEach { it.subordinados.remove(empleadoExistente) }
            empleados.remove(empleadoExistente)
            println("\nEmpleado eliminado exitosamente.")
        } else {
            println("\nEmpleado no encontrado.")
        }
    }

    fun actualizarEmpleado() {
        print("\nIngrese documento de identidad del empleado a actualizar: ")
        val documentoIdentidad = readln()
        val empleadoExistente = empleados.find { it.documentoIdentidad == documentoIdentidad }
        if (empleadoExistente != null) {
            print("\nIngrese nuevo nombre completo del empleado: ")
            val nombreCompleto = readln()
            print("\nIngrese nuevo sexo del empleado (M/F): ")
            val sexo = readln()
            print("\nIngrese nuevo correo electrónico del empleado: ")
            val correoElectronico = readln()
            print("\nIngrese nuevo salario del empleado: ")
            val salario = readln().toDouble()
            print("\nIngrese nuevo nombre de la dependencia del empleado: ")
            val nombreDependencia = readln()
            val dependencia = Dependencia(nombreDependencia)
            print("\nIngrese nuevo año de ingreso del empleado: ")
            val anioIngreso = readln().toInt()
            print("\nIngrese nuevo nombre del cargo del empleado: ")
            val nombreCargo = readln()
            print("\nIngrese nuevo nivel jerárquico del cargo del empleado: ")
            val nivelJerarquico = readln().toInt()
            val cargo = Cargo(nombreCargo, nivelJerarquico)
            
            val nuevoEmpleado = Empleado(nombreCompleto, documentoIdentidad, sexo, correoElectronico, salario, dependencia, anioIngreso, cargo = cargo)
            empleados[empleados.indexOf(empleadoExistente)] = nuevoEmpleado
            println("\nEmpleado actualizado exitosamente.")
        } else {
            println("\nEmpleado no encontrado.")
        }
    }

    fun obtenerEmpleado() {
        print("\nIngrese documento de identidad del empleado a obtener: ")
        val documentoIdentidad = readln()
        val empleado = empleados.find { it.documentoIdentidad == documentoIdentidad }
        if (empleado != null) {
            println("\nEmpleado encontrado: $empleado")
        } else {
            println("\nEmpleado no encontrado.")
        }
    }

    fun agregarCliente() {
        print("\nIngrese nombre completo del cliente: ")
        val nombreCompleto = readln()
        print("\nIngrese documento de identidad del cliente: ")
        val documentoIdentidad = readln()

        if (clientes.any { it.documentoIdentidad == documentoIdentidad }) {
            println("\nYa existe un cliente con ese documento de identidad.")
            return
        }

        print("\nIngrese sexo del cliente (M/F): ")
        val sexo = readln()
        print("\nIngrese correo electrónico del cliente: ")
        val correoElectronico = readln()
        print("\nIngrese dirección de correspondencia del cliente: ")
        val direccionCorrespondencia = readln()
        print("\nIngrese teléfono del cliente: ")
        val telefono = readln()
        
        val cliente = Cliente(nombreCompleto, documentoIdentidad, sexo, correoElectronico, direccionCorrespondencia, telefono)
        clientes.add(cliente)
        println("\nCliente agregado exitosamente.")
        clienteAgregarUsado = true
    }

    fun eliminarCliente() {
        print("\nIngrese documento de identidad del cliente a eliminar: ")
        val documentoIdentidad = readln()
        val clienteExistente = clientes.find { it.documentoIdentidad == documentoIdentidad }
        if (clienteExistente != null) {
            clientes.remove(clienteExistente)
            println("\nCliente eliminado exitosamente.")
        } else {
            println("\nCliente no encontrado.")
        }
    }

    fun actualizarCliente() {
        print("\nIngrese documento de identidad del cliente a actualizar: ")
        val documentoIdentidad = readln()
        val clienteExistente = clientes.find { it.documentoIdentidad == documentoIdentidad }
        if (clienteExistente != null) {
            print("\nIngrese nuevo nombre completo del cliente: ")
            val nombreCompleto = readln()
            print("\nIngrese nuevo sexo del cliente (M/F): ")
            val sexo = readln()
            print("\nIngrese nuevo correo electrónico del cliente: ")
            val correoElectronico = readln()
            print("\nIngrese nueva dirección de correspondencia del cliente: ")
            val direccionCorrespondencia = readln()
            print("\nIngrese nuevo teléfono del cliente: ")
            val telefono = readln()
            
            val nuevoCliente = Cliente(nombreCompleto, documentoIdentidad, sexo, correoElectronico, direccionCorrespondencia, telefono)
            clientes[clientes.indexOf(clienteExistente)] = nuevoCliente
            println("\nCliente actualizado exitosamente.")
        } else {
            println("\nCliente no encontrado.")
        }
    }

    fun obtenerCliente() {
        print("\nIngrese documento de identidad del cliente a obtener: ")
        val documentoIdentidad = readln()
        val cliente = clientes.find { it.documentoIdentidad == documentoIdentidad }
        if (cliente != null) {
            println("\nCliente encontrado: $cliente")
        } else {
            println("\nCliente no encontrado.")
        }
    }

    fun listarEmpleados() {
        println("Listado de Empleados:")
        println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
        println("| Nombre Completo        | Documento | Sexo | Correo Electrónico      | Salario | Dependencia | Año Ingreso | Cargo         | Nivel |")
        println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
        empleados.forEach {
            println("| ${it.nombreCompleto.padEnd(23)} | ${it.documentoIdentidad.padEnd(10)} | ${it.sexo.padEnd(3)} | ${it.correoElectronico.padEnd(24)} | ${it.salario.toString().padEnd(7)} | ${it.dependencia.nombre.padEnd(12)} | ${it.anioIngreso.toString().padEnd(14)} | ${it.cargo.nombre.padEnd(12)} | ${it.cargo.nivelJerarquico.toString().padEnd(5)} |")
        }
        println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    }

    fun listarClientes() {
        println("Listado de Clientes:")
        println("-------------------------------------------------------------------------------")
        println("| Nombre Completo  | Documento | Sexo | Correo Electrónico      | Dirección                | Teléfono    |")
        println("-------------------------------------------------------------------------------")
        clientes.forEach {
            println("| ${it.nombreCompleto.padEnd(17)} | ${it.documentoIdentidad.padEnd(10)} | ${it.sexo.padEnd(3)} | ${it.correoElectronico.padEnd(24)} | ${it.direccionCorrespondencia.padEnd(20)} | ${it.telefono.padEnd(10)} |")
        }
        println("-------------------------------------------------------------------------------")
    }

    fun calcularNominaTotal(): Double {
        return empleados.sumOf { it.salario }
    }

    fun calcularNominaPorDependencia(dependencia: Dependencia): Double {
        return empleados.filter { it.dependencia == dependencia }.sumOf { it.salario }
    }

    fun porcentajeClientesPorSexo(sexo: String): Double {
        val totalClientes = clientes.size
        if (totalClientes == 0) return 0.0
        val clientesPorSexo = clientes.count { it.sexo == sexo }
        return (clientesPorSexo.toDouble() / totalClientes) * 100
    }

    fun cantidadEmpleadosPorCargo(cargo: Cargo): Int {
        return empleados.count { it.cargo == cargo }
    }

    fun empleadoMasAntiguo(): Empleado? {
        return empleados.minByOrNull { it.anioIngreso }
    }

    fun agregarEjemplosEmpleados() {
        val ventas = Dependencia("Ventas")
        val recursosHumanos = Dependencia("Recursos Humanos")
        
        val gerente = Cargo("Gerente", 1)
        val vendedor = Cargo("Vendedor", 2)

        val empleado1 = Empleado("Juan Pérez", "12345678", "M", "juan@empresa.com", 5000.0, ventas, 2015, cargo = vendedor)
        val empleado2 = Empleado("Ana Gómez", "87654321", "F", "ana@empresa.com", 6000.0, recursosHumanos, 2010, cargo = gerente)
        val empleado3 = Empleado("Luis Martínez", "11223322", "M", "luis@empresa.com", 3000.0, ventas, 2018, cargo = vendedor)
        val empleado4 = Empleado("Carmen López", "22334466", "F", "carmen@empresa.com", 3500.0, recursosHumanos, 2016, cargo = vendedor)
        empleados.add(empleado3)
        empleados.add(empleado4)
        empleado1.subordinados.add(empleado3)
        empleado2.subordinados.add(empleado4)
        empleados.add(empleado1)
        empleados.add(empleado2)
        
        println("\n\nDatos de ejemplo de empleados agregados.")
    }

    fun agregarEjemplosClientes() {
        clientes.add(Cliente("Pedro López", "11223344", "M", "pedro@cliente.com", "Av. Siempre Viva 742", "123456789"))
        clientes.add(Cliente("María Ruiz", "22334455", "F", "maria@cliente.com", "Calle Falsa 123", "987654321"))
        
        println("\n\nDatos de ejemplo de clientes agregados.")
    }

    fun agregarSubordinado() {
        print("\nIngrese documento de identidad del empleado principal: ")
        val documentoPrincipal = readln()
        val empleadoPrincipal = empleados.find { it.documentoIdentidad == documentoPrincipal }
        if (empleadoPrincipal != null) {
            print("\nIngrese documento de identidad del subordinado a agregar: ")
            val documentoSubordinado = readln()
            val subordinado = empleados.find { it.documentoIdentidad == documentoSubordinado }
            if (subordinado != null) {
                if (!empleadoPrincipal.subordinados.contains(subordinado)) {
                    empleadoPrincipal.subordinados.add(subordinado)
                    println("\nSubordinado agregado exitosamente.")
                } else {
                    println("\nEl subordinado ya está en la lista.")
                }
            } else {
                println("\nSubordinado no encontrado.")
            }
        } else {
            println("\nEmpleado principal no encontrado.")
        }
    }

    fun eliminarSubordinado() {
        print("\nIngrese documento de identidad del empleado principal: ")
        val documentoPrincipal = readln()
        val empleadoPrincipal = empleados.find { it.documentoIdentidad == documentoPrincipal }
        if (empleadoPrincipal != null) {
            print("\nIngrese documento de identidad del subordinado a eliminar: ")
            val documentoSubordinado = readln()
            val subordinado = empleados.find { it.documentoIdentidad == documentoSubordinado }
            if (subordinado != null) {
                if (empleadoPrincipal.subordinados.remove(subordinado)) {
                    println("\nSubordinado eliminado exitosamente.")
                } else {
                    println("\nEl subordinado no está en la lista.")
                }
            } else {
                println("\nSubordinado no encontrado.")
            }
        } else {
            println("\nEmpleado principal no encontrado.")
        }
    }

    fun listarSubordinados() {
        print("\nIngrese documento de identidad del empleado para listar sus subordinados: ")
        val documento = readln()
        val empleado = empleados.find { it.documentoIdentidad == documento }
        if (empleado != null) {
            if (empleado.subordinados.isNotEmpty()) {
                println("\nSubordinados de ${empleado.nombreCompleto}:")
                empleado.subordinados.forEach {
                    println(" - ${it.nombreCompleto}")
                }
            } else {
                println("\nEl empleado no tiene subordinados.")
            }
        } else {
            println("\nEmpleado no encontrado.")
        }
    }

    fun menu() {
        while (true) {
            print("""

    Menú principal:
    
    1. CRUD Empleados
    2. CRUD Clientes
    3. Consultas
    4. Salir
    
    Ingrese la opción deseada: """)
            
            when (readln().toInt()) {
                1 -> submenuEmpleados()
                2 -> submenuClientes()
                3 -> consultas()
                4 -> return
                else -> println("\nOpción no válida.")
            }
        }
    }

    private fun submenuEmpleados() {
        var empleadoAgregarDisponible = !empleadoAgregarUsado
        while (true) {
            println("""

    Submenú Empleados:

    1. Agregar empleado
    2. Eliminar empleado
    3. Actualizar empleado
    4. Obtener empleado
    5. Listar todos los empleados
    6. Agregar subordinado
    7. Eliminar subordinado
    8. Listar subordinados""")
            if (empleadoAgregarDisponible) println("    9. Agregar datos de ejemplo")
            println("    10. Volver al menú principal")
            print("\n    Ingrese la opción deseada: ")
            when (readln().toInt()) {
                1 -> agregarEmpleado()
                2 -> eliminarEmpleado()
                3 -> actualizarEmpleado()
                4 -> obtenerEmpleado()
                5 -> listarEmpleados()
                6 -> agregarSubordinado()
                7 -> eliminarSubordinado()
                8 -> listarSubordinados()
                9 -> {
                    if (empleadoAgregarDisponible) {
                        agregarEjemplosEmpleados()
                        empleadoAgregarUsado = true
                        empleadoAgregarDisponible = false
                    } else {
                        println("\nLa opción de agregar datos de ejemplo de empleados ya ha sido utilizada.")
                    }
                }
                10 -> return
                else -> println("\nOpción no válida.")
            }
        }
    }

    private fun submenuClientes() {
        var clienteAgregarDisponible = !clienteAgregarUsado
        while (true) {
            println("""

    Submenú Clientes:
    
    1. Agregar cliente
    2. Eliminar cliente
    3. Actualizar cliente
    4. Obtener cliente
    5. Listar todos los clientes""")
            if (clienteAgregarDisponible) println("    6. Agregar datos de ejemplo")
            println("    7. Volver al menú principal")
            print("\n    Ingrese la opción deseada: ")
            when (readln().toInt()) {
                1 -> agregarCliente()
                2 -> eliminarCliente()
                3 -> actualizarCliente()
                4 -> obtenerCliente()
                5 -> listarClientes()
                6 -> {
                    if (clienteAgregarDisponible) {
                        agregarEjemplosClientes()
                        clienteAgregarUsado = true
                        clienteAgregarDisponible = false
                    } else {
                        println("\nLa opción de agregar datos de ejemplo de clientes ya ha sido utilizada.")
                    }
                }
                7 -> return
                else -> println("\nOpción no válida.")
            }
        }
    }

    private fun consultas() {
        while (true) {
            println("""

    Consultas:
    
    1. Calcular nómina total de empleados
    2. Calcular nómina por dependencia
    3. Porcentaje de clientes por sexo
    4. Cantidad de empleados por cargo
    5. Empleado más antiguo
    6. Volver al menú principal""")
            print("\n    Ingrese la opción deseada: ")
            when (readln().toInt()) {
                1 -> println("La nómina total de empleados es: ${calcularNominaTotal()}")
                2 -> {
                    print("\nIngrese el nombre de la dependencia: ")
                    val nombreDependencia = readln()
                    val dependencia = Dependencia(nombreDependencia)
                    println("La nómina total por dependencia es: ${calcularNominaPorDependencia(dependencia)}")
                }
                3 -> {
                    print("\nIngrese el sexo (M/F): ")
                    val sexo = readln()
                    println("El porcentaje de clientes de sexo $sexo es: ${porcentajeClientesPorSexo(sexo)}%")
                }
                4 -> {
                    print("\nIngrese nombre del cargo: ")
                    val nombreCargo = readln()
                    print("\nIngrese nivel jerárquico del cargo: ")
                    val nivelJerarquico = readln().toInt()
                    val cargo = Cargo(nombreCargo, nivelJerarquico)
                    println("La cantidad de empleados con el cargo $nombreCargo es: ${cantidadEmpleadosPorCargo(cargo)}")
                }
                5 -> {
                    val empleadoAntiguo = empleadoMasAntiguo()
                    if (empleadoAntiguo != null) {
                        println("El empleado más antiguo es: $empleadoAntiguo")
                    } else {
                        println("No hay empleados registrados.")
                    }
                }
                6 -> return
                else -> println("\nOpción no válida.")
            }
        }
    }
}

fun main() {
    print("\nIngrese razón social de la empresa: ")
    val razonSocial = readln()
    print("\nIngrese NIT de la empresa: ")
    val nit = readln()
    print("\nIngrese dirección de la empresa: ")
    val direccion = readln()

    val empresa = Empresa(razonSocial, nit, direccion)
    empresa.menu()
}