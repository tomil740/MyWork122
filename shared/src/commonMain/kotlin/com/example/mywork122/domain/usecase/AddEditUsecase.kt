package com.example.mywork122.domain.usecase

data class AddEditUsecase (
    val onlegalityCheck:OnLegalityCheck,
    val onSubmitDeclare: OnSubmitDeclare,
    val getDeclareById: GetDeclareById,
    val onDeleteDeclareById:OnDeleteDeclareById

)