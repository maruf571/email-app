<#import "layout/layout.ftl" as layout>
<@layout.default>

    <span class="preheader">
        Thanks for trying out ${mailModel.productName}.
        We’ve pulled together some information and resources to help you get started.
    </span>
    <table class="email-wrapper" width="100%" cellpadding="0" cellspacing="0" role="presentation">
        <tr>
            <td align="center">
                <table class="email-content" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                    <tr>
                        <td class="email-masthead"></td>
                    </tr>
                    <!-- Email Body -->
                    <tr>
                        <td class="email-body" width="570" cellpadding="0" cellspacing="0">
                            <table class="email-body_inner" align="center" width="570" cellpadding="0" cellspacing="0"
                                   role="presentation">
                                <!-- Body content -->
                                <tr>
                                    <td class="content-cell">
                                        <div class="f-fallback">
                                            <h1>Welcome,</h1>
                                            <p>Thanks for trying ${mailModel.productName}. We're thrilled to have you on board. To
                                                get the most out of ${mailModel.productName}, do this primary next step:</p>
                                            <!-- Action -->
                                            <table class="body-action" align="center" width="100%" cellpadding="0"
                                                   cellspacing="0" role="presentation">
                                                <tr>
                                                    <td align="center">
                                                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                                               role="presentation">
                                                            <tr>
                                                                <td align="center">
                                                                    <a href="${mailModel.activationLink}" class="f-fallback button"
                                                                       style="color: white"
                                                                       target="_blank">Active your account</a>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                            <p>If you have any questions,
                                                feel free to email our customer support team at
                                                <a href="mailto:${mailModel.supportEmail}">${mailModel.supportEmail}</a>.
                                                We also offer
                                                <a href="${mailModel.chatLink}" target="_blank">live chat</a>
                                                during business hours.
                                            </p>
                                            <p>Thanks,
                                                <br>${mailModel.senderName} and the  ${mailModel.productName} Team</p>

                                            <!-- Sub copy -->
                                            <table class="body-sub" role="presentation">
                                                <tr>
                                                    <td>
                                                        <p class="f-fallback sub">If you're having trouble with the
                                                            button above, copy and paste the URL below into your web
                                                            browser.</p>
                                                        <p class="f-fallback sub">${mailModel.activationLink}</p>
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
                        <td class="email-masthead"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</@layout.default>
