<#import "layout/layout.ftl" as layout>
<@layout.default>

<table class="email-wrapper" width="100%" cellpadding="0" cellspacing="0" role="presentation">
    <tr>
        <td align="center">
            <table class="email-content" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                    <td class="email-masthead">
                    </td>
                </tr>
                <!-- Email Body -->
                <tr>
                    <td class="email-body" width="570" cellpadding="0" cellspacing="0">
                        <table class="email-body_inner" align="center" width="570" cellpadding="0" cellspacing="0" role="presentation">
                            <!-- Body content -->
                            <tr>
                                <td class="content-cell">
                                    <div class="f-fallback">
                                        <h1>Hi,</h1>
                                        <p>You recently requested to reset your password for your ${mailModel.productName} account.
                                            Use the button below to reset it. <strong>This password reset is only valid for the next 24 hours.</strong></p>
                                        <!-- Action -->
                                        <table class="body-action" align="center" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                                            <tr>
                                                <td align="center">
                                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" role="presentation">
                                                        <tr>
                                                            <td align="center">
                                                                <a href="${mailModel.resetPasswordLink}" class="f-fallback button button--green" target="_blank">Reset your password</a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <p>
                                            If you did not request a password reset,
                                            please ignore this email or contact with customer support team at
                                            <a href="mailto:${mailModel.supportEmail}">${mailModel.supportEmail}</a>
                                            if you have questions.</p>
                                        <p>Thanks,
                                            <br> ${mailModel.productName}</p>
                                        <!-- Sub copy -->
                                        <table class="body-sub" role="presentation">
                                            <tr>
                                                <td>
                                                    <p class="f-fallback sub">If you're having trouble with the button above, copy and paste the URL below into your web browser.</p>
                                                    <p class="f-fallback sub">${mailModel.resetPasswordLink}</p>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="email-masthead">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</@layout.default>
