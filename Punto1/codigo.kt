fun divisionRecursiva(dividendo: Int, divisor: Int, cociente: Int): Int
{
    if(dividendo < divisor )
    {
        return cociente 
    }
    
    println("Restando: $dividendo - $divisor = ${dividendo - divisor}" )
    
    return divisionRecursiva(dividendo - divisor, divisor, cociente+1)
}

fun main() {
    println("Ingrese el dividendo: ")
    val dividendo = readln().toInt()
    println("Ingrese el divisor: ")
    val divisor = readln().toInt()
    val resultado = divisionRecursiva(dividendo, divisor, 0)
    println("$dividendo/$divisor = $resultado")
}