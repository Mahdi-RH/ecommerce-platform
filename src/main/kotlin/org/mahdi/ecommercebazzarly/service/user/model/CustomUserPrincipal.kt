package org.mahdi.ecommercebazzarly.service.user.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserPrincipal(
    val userId: Long,
    private val email: String,
    private val password: String,
    private val authorities: Collection<GrantedAuthority> = emptyList()
) : UserDetails {

    override fun getAuthorities() = authorities

    override fun getPassword() = password

    override fun getUsername() = email

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}