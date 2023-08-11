package com.example.heartstone_repository.utils

class BadRequestException(message: String = "Requisição inválida. Por favor, verifique os dados enviados e tente novamente.") : Exception(message)

class UnauthorizedException(message: String = "Não autorizado. Por favor, verifique suas credenciais ou token de autenticação.") : Exception(message)

class ForbiddenException(message: String = "Acesso proibido. Você não tem permissão para acessar este recurso.") : Exception(message)

class NotFoundException(message: String = "Recurso não encontrado. O recurso solicitado não existe ou foi removido.") : Exception(message)

class InternalServerErrorException(message: String = "Erro interno do servidor. Por favor, tente novamente mais tarde.") : Exception(message)
class GenericException(message: String = "Desculpe, ocorreu um erro inesperado. Por favor, tente novamente mais tarde.") : Exception(message)