using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TargetController : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }

    void OnCollisionEnter(Collision collision)
    {
        //Debug.Log("Hit"); // ログを表示する
        //GetComponent<Renderer>().material.color = Color.red;
        if (this.GetComponent<Renderer>().material.color == Color.black) {
            GetComponent<Renderer>().material.color = Color.cyan;
        }else{
            GetComponent<Renderer>().material.color = Color.black;
        }
    }
}
