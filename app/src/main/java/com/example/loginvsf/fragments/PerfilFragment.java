package com.example.loginvsf.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.loginvsf.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PerfilFragment extends Fragment {

    private static final String KEY_IMAGEM = "";
    private static final String KEY_IMAGEM_BANNER = "";
    ImageView imgCamera;
    ImageView imgCameraBanner;
    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<Intent> cameraLauncherBanner;

    Bitmap perfilBitmap;
    Bitmap bannerBitmap;

    // OPERANDO

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        imgCamera = view.findViewById(R.id.imgCamera);
        imgCameraBanner = view.findViewById(R.id.imgCameraBanner);

        // Inicialize o ActivityResultLauncher
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == requireActivity().RESULT_OK) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imgCamera.setImageBitmap(bitmap);
                        perfilBitmap = bitmap;
                    }
                });

        cameraLauncherBanner = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == requireActivity().RESULT_OK) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");

                        // Redimensiona a imagem para o formato retangular
                        int width = bitmap.getWidth();
                        int height = width / 2; // A altura será metade da largura para criar um retângulo horizontal
                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

                        // Salva a imagem redimensionada em um arquivo
                        FileOutputStream out = null;
                        try {
                            File file = new File(requireContext().getExternalFilesDir(null), "retangle_image.jpg");
                            out = new FileOutputStream(file);
                            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            // A imagem redimensionada será salva como um arquivo JPEG com qualidade 100
                            // Você pode ajustar a qualidade conforme necessário
                            out.flush();
                            out.close();

                            // Carrega a imagem redimensionada na ImageView retangular
                            imgCameraBanner.setImageBitmap(resizedBitmap);
                            imgCameraBanner.setScaleType(ImageView.ScaleType.CENTER_CROP);

                            // Atualiza a variável bannerBitmap com a imagem redimensionada
                            bannerBitmap = resizedBitmap;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


        // Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(intent);
            }
        });

        imgCameraBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncherBanner.launch(intent);
            }
        });

        // Restaure as imagens do perfil e do banner quando o fragmento for recriado
        if (perfilBitmap != null) {
            imgCamera.setImageBitmap(perfilBitmap);
        }

        if (bannerBitmap != null) {
            imgCameraBanner.setImageBitmap(bannerBitmap);
        }

        return view;
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado da imagem no Bundle
        outState.putParcelable(KEY_IMAGEM, perfilBitmap);
        outState.putParcelable(KEY_IMAGEM_BANNER, bannerBitmap);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializar seus componentes, incluindo o ImageView
        imgCamera = view.findViewById(R.id.imgCamera);
        imgCameraBanner = view.findViewById(R.id.imgCameraBanner);

        // Verificar se há um estado salvo (quando a tela é recriada)
        if (savedInstanceState != null) {
            // Restaurar a imagem a partir do estado salvo
            perfilBitmap = savedInstanceState.getParcelable(KEY_IMAGEM);
            if (perfilBitmap != null) {
                imgCamera.setImageBitmap(perfilBitmap);
            }
        }// Verificar se há um estado salvo (quando a tela é recriada)
        if (savedInstanceState != null) {
            // Restaurar a imagem a partir do estado salvo
            bannerBitmap = savedInstanceState.getParcelable(KEY_IMAGEM_BANNER);
            if (bannerBitmap != null) {
                imgCameraBanner.setImageBitmap(bannerBitmap);
            }
        }

    }

}
