package ru.m4bank.directposapi.cashbox.data.fiscal

/**
 * @property labelVerificationResult - product label check result
 * @property vatValue - VAT rate
 * @property billingPurpose - billing purpose
 * @property billingMethod - billing method
 */
data class FiscalData(
    val labelVerificationResult: String? = null,
    val vatValue: VatValue,
    val billingPurpose: BillingPurpose,
    val billingMethod: BillingMethod,
)

data class VatValue(
    val code: Int,
    val description: String,
)

data class BillingPurpose(
    val code: Int,
    val description: String,
)

data class BillingMethod(
    val code: Int,
    val description: String,
)
