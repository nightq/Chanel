package com.nightq.freedom.chanel.mvp.login.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.nightq.freedom.chanel.R;
import com.nightq.freedom.chanel.application.MainApplication;
import com.nightq.freedom.chanel.base.baseComponent.ApplicationComponent;
import com.nightq.freedom.chanel.base.widgets.BaseActivity;
import com.nightq.freedom.chanel.baseModels.UserModel;
import com.nightq.freedom.chanel.mvp.home.views.MainActivity;
import com.nightq.freedom.chanel.mvp.login.modules.LoginModule;
import com.nightq.freedom.chanel.mvp.login.presenters.ILoginPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @Inject
    ILoginPresenter iLoginPresenter;

    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.txt_account)
    EditText txtAccount;
    @Bind(R.id.txt_password)
    EditText txtPassword;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;

    private Subscription txtAccountSubscription;
    private Subscription txtPasswordSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txtAccountSubscription =
                RxTextView.textChangeEvents(txtAccount)
                        .subscribe(this::txtAccountChange);
        txtPasswordSubscription =
                RxTextView.textChangeEvents(txtPassword)
                        .subscribe(this::txtPasswordChange);

        iLoginPresenter.initCheckAuth();
    }

    /**
     * 编辑账户时
     *
     * @param textViewTextChangeEvent
     */
    public void txtAccountChange(TextViewTextChangeEvent textViewTextChangeEvent) {
        showAccountError(null);
    }

    /**
     * 编辑密码时
     *
     * @param textViewTextChangeEvent
     */
    public void txtPasswordChange(TextViewTextChangeEvent textViewTextChangeEvent) {
        showPasswordError(null);
    }

    public void showAccountError(String error) {
//        txtAccount.setError(error);
        txtAccount.setError(getString(R.string.error_invalid_email));
        txtAccount.requestFocus();
    }

    public void showPasswordError(String error) {
        txtPassword.setError(getString(R.string.error_incorrect_password));
        txtPassword.requestFocus();
    }


    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {
        MainApplication.getInstance(this)
                .getApplicationComponent()
                .plus(new LoginModule(this))
                .inject(this);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void cliclLogin(View view) {
        // Reset errors.
        txtAccount.setError(null);
        txtPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = txtAccount.getText().toString();
        String password = txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            txtPassword.setError(getString(R.string.error_invalid_password));
            focusView = txtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            txtAccount.setError(getString(R.string.error_field_required));
            focusView = txtAccount;
            cancel = true;
        } else if (!isEmailValid(email)) {
            txtAccount.setError(getString(R.string.error_invalid_email));
            focusView = txtAccount;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            if (R.id.btn_register == view.getId()) {
                iLoginPresenter.startRegister(email, password);
            } else {
                iLoginPresenter.startLogining(email, password);
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void loginSuccess(UserModel userModel) {
        MainApplication.getInstance(this).createUserComponent(userModel);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            emailLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            emailLoginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    emailLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            emailLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        txtPasswordSubscription.unsubscribe();
        txtAccountSubscription.unsubscribe();
    }

}

