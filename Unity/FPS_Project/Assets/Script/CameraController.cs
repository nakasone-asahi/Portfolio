using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{
    [SerializeField] private GameObject mainCamera;     //メインカメラ格納用
    [SerializeField] private GameObject AimAssist;     //照準(サイト)格納用
 
    private int hantei = 0;
    private float yRot;
    private float currentYRot;
    private float yRotVelocity;
 
    //呼び出し時に実行される関数
    void Start()
    {
        
    }
 
 
    //単位時間ごとに実行される関数
    void Update()
    {
        if(hantei == 0){
            yRot += Input.GetAxis("Mouse X") * 5f; //マウスの移動.
            currentYRot = Mathf.SmoothDamp(currentYRot, yRot, ref yRotVelocity, 0.1f);
            transform.rotation = Quaternion.Euler(0, currentYRot, 0);
        }else{
            yRot += Input.GetAxis("Mouse X") * 1f; //マウスの移動.
            currentYRot = Mathf.SmoothDamp(currentYRot, yRot, ref yRotVelocity, 0.1f);
            transform.rotation = Quaternion.Euler(0, currentYRot, 0);
        }

        if (Input.GetKeyDown(KeyCode.F))
        {
            if(hantei == 0){
                hantei=1;
            }else{
                hantei=0;
            }
        }
    }
}
