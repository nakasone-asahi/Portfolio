using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hanter : MonoBehaviour
{
    // Start is called before the first frame update
    public Transform player;//プレイヤーの位置を取得するためのTransform型の変数
    private int Mode = 1 ;//敵の行動パターンを分けるための変数
    public Animator zombie_death;//アニメーション
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
        if(Mode == 0){
            //モード0でその場にとどまります
        }else{
        transform.position += transform.forward * 1f * Time.deltaTime;
        this.transform.LookAt(player);
        }
    }

    void OnTriggerEnter(Collider other)
    {
		if (other.CompareTag("bullet"))
        {
            zombie_death.SetTrigger("death");
            Destroy(gameObject, 3);
            Mode = 0;
        }
    }
}
