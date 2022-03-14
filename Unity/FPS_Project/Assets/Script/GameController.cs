using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField] private GameObject mainCamera;     //メインカメラ格納用
    [SerializeField] private GameObject AimAssist;     //照準(サイト)格納用
    private int hantei = 0;

    void Start()
    {
    }
    // Update is called once per frame
    void Update()
    {
        //Fキーが押されたら、カメラを切り替える
        if (Input.GetKeyDown(KeyCode.F))
        {
            if (hantei == 0)
            {
                hantei = 1;
                //以下エイムカメラに切り替え
                AimAssist.SetActive(false);
                GameObject.Find("Gun").transform.localPosition = new Vector3(0, (float)-0.15, (float)-0.1);
                
            }
            else
            {
                hantei = 0;
                //以下メインカメラに切り替え
                AimAssist.SetActive(true);
                GameObject.Find("Gun").transform.localPosition = new Vector3((float)0.267, (float)-0.288, (float)0.274);
            }
        }
        
    }

}
