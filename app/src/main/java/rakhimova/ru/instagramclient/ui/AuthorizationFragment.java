package rakhimova.ru.instagramclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;

public class AuthorizationFragment extends Fragment {

    private static final String EMPTY_STRING = "";

    @BindView(R.id.login)
    TextInputLayout login;

    @BindView(R.id.password)
    TextInputLayout password;

    @BindView(R.id.ok)
    MaterialButton ok;

    @BindView(R.id.share_star)
    ImageView star;

    public AuthorizationFragment() {
    }

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_authorization, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        star.setOnClickListener((v) -> {
            Fragment galleryFragment = GalleryFragment.newInstance();
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setPathMotion(new ArcMotion());
            changeBounds.setInterpolator(new OvershootInterpolator(1.5f));
            galleryFragment.setSharedElementEnterTransition(changeBounds);

            if (getFragmentManager() != null) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_content, galleryFragment)
                        .addToBackStack(this.getClass().getName())
                        .addSharedElement(star, getString(R.string.share_star))
                        .commit();
            }
        });
        ok.setOnClickListener(v -> {
            checkLogin();
            checkPassword();
        });
    }

    public void checkLogin() {
        EditText editLogin = login.getEditText();
        if (editLogin == null) return;
        String userLogin = String.valueOf(editLogin.getText());
        if (userLogin.equals(EMPTY_STRING)) {
            login.getEditText().setError("Введите логин");
            YoYo.with(Techniques.Shake)
                    .repeat(2)
                    .playOn(login);
        }
    }

    public void checkPassword() {
        EditText editPassword = password.getEditText();
        if (editPassword == null) return;
        String userPassword = String.valueOf(editPassword.getText());
        if (userPassword.equals(EMPTY_STRING)) {
            password.getEditText().setError("Введите пароль");
            YoYo.with(Techniques.Shake)
                    .repeat(2)
                    .playOn(password);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}