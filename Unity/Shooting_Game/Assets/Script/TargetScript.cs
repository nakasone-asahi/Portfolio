using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TargetScript : MonoBehaviour
{
    
    public GameObject TargetA;
    public GameObject TargetB;
    public GameObject TargetC;
    public GameObject TargetD;
    public GameObject TargetE;
    public Color material;//的の初期のマテリアルカラー
    private Animator animA;  //Animatorをanimという変数で定義する
    private Animator animB; 
    private Animator animC;
    private Animator animD;
    private Animator animE;

    void Start()
    {
        TargetA = GameObject.Find("Target1");
        TargetB = GameObject.Find("Target2");
        TargetC = GameObject.Find("Target3");
        TargetD = GameObject.Find("Target4");
        TargetE = GameObject.Find("Target5");
        //的の初期設定
        animA = TargetA.GetComponent<Animator>();
        animA.SetBool("blTarget", true);
        animB = TargetB.GetComponent<Animator>();
        animB.SetBool("blTarget", true);
        animD = TargetD.GetComponent<Animator>();
        animD.SetBool("blTarget", true);
        animE = TargetE.GetComponent<Animator>();
        animE.SetBool("blTarget", true);

        animC = TargetC.GetComponent<Animator>();
        material = TargetC.GetComponent<Renderer>().material.color;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnCollisionEnter(Collision collision)
    {
        //Debug.Log("Hit"); // ログを表示する
        if (this.GetComponent<Renderer>().material.color == Color.red) {
            GetComponent<Renderer>().material.color = material;
        }else{
            GetComponent<Renderer>().material.color = Color.red;
        }
        //Invoke("nextTarget", 0.5f);
    }

    public void TargetAHit()
    {
        animA.SetBool("blTarget", true);
        Invoke("nextTarget", 0.5f);
    }
    public void TargetBHit()
    {
        animB.SetBool("blTarget", true);
        Invoke("nextTarget", 0.5f);
    }
    public void TargetCHit()
    {
        animC.SetBool("blTarget", true);
        Invoke("nextTarget", 0.5f);
    }
    public void TargetDHit()
    {
        animD.SetBool("blTarget", true);
        Invoke("nextTarget", 0.5f);
    }
    public void TargetEHit()
    {
        animE.SetBool("blTarget", true);
        Invoke("nextTarget", 0.5f);
    }

    void nextTarget(){

        int number = Random.Range(1,5);
        //switch文
        switch(number){
            //条件１
            case 1:
                //処理１
                animA.SetBool("blTarget", false);
                //break文
                break;
            //条件２
            case 2:
                //処理２
                animB.SetBool("blTarget", false);
                //break文
                break;
            //条件３
            case 3:
                //処理３
                animC.SetBool("blTarget", false);
                //break文
                break;
            //条件4
            case 4:
                //処理4
                animD.SetBool("blTarget", false);
                //break文
                break;
            //条件5
            case 5:
                //処理5
                animE.SetBool("blTarget", false);
                //break文
                break;
        }
    }
}
