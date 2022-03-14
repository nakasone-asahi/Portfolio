using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UnityEngine.UI;
using MagicLeapTools;
#if PLATFORM_LUMIN
using UnityEngine.XR.MagicLeap;
#endif

public class GameController : MonoBehaviour
{
    //MagicLeapControllerのオブジェクト
    public ControlInput controlInput;
    //弾丸オブジェクト
    public GameObject bullet;
    public GameObject GunPosition;
    public GameObject BulletPosition;
    public float speed = 1000;
    public Vector3 defultpos;
    //エフェクト
    [SerializeField]  Vector3 muzzleFlashScale;
    [SerializeField]  GameObject muzzleFlashPrefab;
    [SerializeField]  GameObject hitEffectPrefab;
    GameObject muzzleFlash;
    GameObject hitEffect;
    //銃
    public GameObject Gun;
    private Animator anim;  //Animatorをanimという変数で定義する

    // Start is called before the first frame update
    void Start()
    {
        Gun = GameObject.Find("M1911");
        controlInput.OnTriggerDown.AddListener(HandleTriggerDown);
        anim = Gun.GetComponent<Animator>();
        anim.SetBool("blshoot", false);
    }

    // Update is called once per frame
    void Update()
    {    
        defultpos = controlInput.Position;
        defultpos.x = controlInput.Position.x - 0.03f ;
        defultpos.y = controlInput.Position.y - 0.07f;
        defultpos.z = controlInput.Position.z - 0.55f ;
        GunPosition.transform.position =  defultpos;
        GunPosition.transform.eulerAngles = controlInput.Orientation.eulerAngles;
    }

     private void HandleTriggerDown(){
        //トリガー押下時、弾の発射
        //弾をインスタンス化して装填
            GameObject createdBullet = Instantiate(bullet) as GameObject;
            createdBullet.transform.position = BulletPosition.transform.position;
            //発射ベクトル
            Vector3 force;
            //発射の向きと速度を決定
            force = BulletPosition.transform.forward * speed;
            //銃のエフェクト
            anim.SetBool("blshoot", true);
            //発砲エフェクトのオン
            if (muzzleFlashPrefab != null){
              if(muzzleFlash != null){
                //muzzleFlash.SetActive(true);
              }
              else{
                muzzleFlash = Instantiate(muzzleFlashPrefab,BulletPosition.transform.position,BulletPosition.transform.rotation);
                muzzleFlash.transform.SetParent(BulletPosition.transform);
                muzzleFlash.transform.localScale = muzzleFlashScale;
              }
            }
            // Rigidbodyに力を加えて発射(重力をなしに)
            createdBullet.GetComponent<Rigidbody>().useGravity = false;
            createdBullet.GetComponent<Rigidbody>().AddForce(force);
            Destroy(createdBullet, 3);
            //発砲エフェクトのオフ
            if(muzzleFlash != null){
              Invoke("muzzleFlashOff", 0.1f);
              //muzzleFlash.SetActive(false);
            }
            //ヒットエフェクトOFF
            if(hitEffect != null){
              if (hitEffect.activeSelf){
                hitEffect.SetActive(false);
              }
            }
        }

        public void muzzleFlashOff()
        {
           muzzleFlash.SetActive(false);
           anim.SetBool("blshoot", false);
        }
     
}
