package org.crolopez.sharedexpense.shared.infrastructure.authentication

import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.user.application.services.UserService
import org.reactivestreams.Publisher

@Singleton
class AuthenticationProviderImpl : AuthenticationProvider {

    @Inject
    lateinit var userService: UserService

    override fun authenticate(
        httpReq: HttpRequest<*>?, authReq: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse?>? {
        if (httpReq == null
            || httpReq.methodName != HttpMethod.POST.name
            || httpReq.path != "/login")
            return Flowable.just(AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH))

        val username = authReq.identity.toString()
        val password = authReq.secret.toString()
        val userEntity = userService.getUser(username)

        return Flowable.just(
            when (userEntity.isPresent) {
                true -> when (userEntity.get().password == password) {
                            true -> AuthenticationResponse.success(username)
                            false-> AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)
                        }
                false -> AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND)
        })
    }
}
