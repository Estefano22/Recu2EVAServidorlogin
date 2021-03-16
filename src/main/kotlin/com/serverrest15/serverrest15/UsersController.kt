package com.serverrest15.serverrest15

import org.springframework.web.bind.annotation.*

import java.security.MessageDigest
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


private fun cifrar(textoEnString : String, llaveEnString : String) : String {
    println("Voy a cifrar $textoEnString")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, getKey(llaveEnString))
    val textCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(textoEnString.toByteArray(Charsets.UTF_8)))
    println("He obtenido $textCifrado")
    return textCifrado
}
@Throws(BadPaddingException::class)
private fun descifrar(textoCifrrado : String, llaveEnString : String) : String {
    println("Voy a descifrar $textoCifrrado")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, getKey(llaveEnString));
    val textDescifrado = String(cipher.doFinal(Base64.getDecoder().decode(textoCifrrado)))
    println("He obtenido $textDescifrado")
    return textDescifrado
}


private fun getKey(llaveEnString : String): SecretKeySpec {
    var llaveUtf8 = llaveEnString.toByteArray(Charsets.UTF_8)
    val sha = MessageDigest.getInstance("SHA-1")
    llaveUtf8 = sha.digest(llaveUtf8)
    llaveUtf8 = llaveUtf8.copyOf(16)
    return SecretKeySpec(llaveUtf8, "AES")
}

@RestController
class UsersController(private val UsuarioR : UsersRepository) {

    @GetMapping("/usuarios")
    fun getAllusuarios(): List<Users> {
        return UsuarioR.findAll()
    }

    @PostMapping("/usuarios")
    fun insertusuarios(@RequestBody Usuarios: Users) {
        UsuarioR.save(Usuarios)
    }

    @GetMapping("/usuarios/{id}")
    fun getusuarios(@PathVariable id: Long): Users {
        val cifrar= id.toString();
        val key="FUAA"
        val descifrada=descifrar(cifrar(cifrar,key),key)
        return UsuarioR.findById(descifrada.toLong()).get()
    }

    @DeleteMapping("/usuarios/{id}")
    fun deleteusuarios(@PathVariable id: Long) {
        UsuarioR.deleteById(id)
    }
}

